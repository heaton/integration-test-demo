package me.heaton.spring.db;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class IntegrationTestWithMongo {

  private static final int PORT = 12345;

  private MongodExecutable mongodExe;
  private MongodProcess mongod;
  private MongoClient mongo;

  @Test
  public void should_insert_a_book () {
    final MongoDatabase db = mongo.getDatabase("test");
    final MongoCollection<Document> books = db.getCollection("book");
    books.drop();
    Document book = new Document("name", "Clean Code")
        .append("author", "Robert C. Martin")
        .append("description", "A Handbook of Agile Software Craftsmanship");
    books.insertOne(book);
    assertThat(books.find().first(), is(book));
  }

  @Before
  public void start_a_server() throws IOException {
    startEmbeddedMongo();
    connectMongo();
  }

  private void startEmbeddedMongo() throws IOException {
    MongodStarter starter = MongodStarter.getDefaultInstance();

    IMongodConfig mongoConfig = new MongodConfigBuilder()
        .version(Version.Main.PRODUCTION)
        .net(new Net(PORT, Network.localhostIsIPv6()))
        .build();

    mongodExe = starter.prepare(mongoConfig);
    mongod = mongodExe.start();
  }

  private void connectMongo() {
    mongo = new MongoClient("localhost", PORT);
  }

  @After
  public void stop_mongo_server() {
    if(mongo != null) mongo.close();
    if(mongod != null) mongod.stop();
    if(mongodExe != null) mongodExe.stop();
  }

}
