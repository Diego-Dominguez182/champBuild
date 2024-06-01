package mx.uv.djdl.champBuild;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ChampBuildRepository {

    @Autowired
    private MongoClient mongoClient;

    private MongoCollection<Document> collection;


    @jakarta.annotation.PostConstruct
    public void initialize() {
            MongoDatabase database = mongoClient.getDatabase("Lol");
            collection = database.getCollection("Builds");
        
    }

    public List<ChampBuild> findAll() {
        List<ChampBuild> builds = new ArrayList<>();
        for (Document doc : collection.find()) {
            ChampBuild build = documentToChampBuild(doc);
            builds.add(build);
        }
        return builds;
    }

    public List<ChampBuild> findByName(String name) {
        List<ChampBuild> buildsByName = new ArrayList<>();
        Document query = new Document("champName", name);
        for (Document doc : collection.find(query)){
            ChampBuild build = documentToChampBuild(doc);
            buildsByName.add(build);
        }
        return buildsByName;
    }

    public void save(ChampBuild build) {
        Document doc = champBuildToDocument(build);
        collection.insertOne(doc);
    }

    public void update(ChampBuild build) {
        Document query = new Document("champName", build.getChampName());
        Document doc = champBuildToDocument(build);
        collection.replaceOne(query, doc);
    }

    public void delete(String name) {
        Document query = new Document("champName", name);
        collection.deleteOne(query);
    }

    private Document champBuildToDocument(ChampBuild build) {
        return new Document("champName", build.getChampName())
                .append("items", build.getItems())
                .append("userName", build.getUserName());
    }

    private ChampBuild documentToChampBuild(Document doc) {
        ChampBuild build = new ChampBuild();
        build.setChampName(doc.getString("champName"));
        build.setItems((List<String>) doc.get("items"));
        build.setUserName(doc.getString("userName"));
        return build;
    }
}
