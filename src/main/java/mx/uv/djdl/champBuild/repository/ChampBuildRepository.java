package mx.uv.djdl.champBuild.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import mx.uv.djdl.champBuild.model.ChampBuild;
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
        for (Document doc : collection.find(query)) {
            ChampBuild build = documentToChampBuild(doc);
            buildsByName.add(build);
        }
        return buildsByName;
    }

    public void save(ChampBuild build) {
        Document query1 = new Document("buildTitle", build.getBuildTitle());
        if (collection.find(query1).first() != null) {
            throw new IllegalArgumentException("Ya existe una una build con el mismo título");
        }
        Document query2 = new Document("items", build.getItems())
        .append("champName", build.getChampName());
        if (collection.find(query2).first() != null) {
            throw new IllegalArgumentException("Ya existe una una build con los mismos items para este campeón");
        }
        Document doc = champBuildToDocument(build);
        collection.insertOne(doc);
    }
   public void update(ChampBuild build) {
        Document query = new Document("champName", build.getChampName())
              .append("userName", build.getUserName())
              .append("buildTitle", build.getBuildTitle());
        if (collection.find(query).first() != null) {
            Document doc = champBuildToDocument(build);
            collection.replaceOne(query, doc);
        } else {
            throw new IllegalArgumentException("La build a actualizar no existe");
        }
    }

    public void delete(String buildTitle) {
        Document query = new Document("buildTitle", buildTitle);
        if (collection.find(query).first() != null) {
            collection.deleteOne(query);
        } else {
            throw new IllegalArgumentException("La build a eliminar no existe");
        }
    }

    private Document champBuildToDocument(ChampBuild build) {
        return new Document("buildTitle", build.getBuildTitle())
                .append("champName", build.getChampName())
                .append("items", build.getItems())
                .append("userName", build.getUserName());
    }

    private ChampBuild documentToChampBuild(Document doc) {
        ChampBuild build = new ChampBuild();
        build.setBuildTitle(doc.getString("buildTitle"));
        build.setChampName(doc.getString("champName"));
        build.setItems((List<String>) doc.get("items"));
        build.setUserName(doc.getString("userName"));
        return build;
    }
}
