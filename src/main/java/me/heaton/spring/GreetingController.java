package me.heaton.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @RequestMapping("/init")
  public void init() {
  }

  @RequestMapping("/greeting")
  public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
    return new Greeting(counter.incrementAndGet(),
        String.format(template, name));
  }

  @RequestMapping("/greeting-map")
  public Map<String, Object> greetingMap(@RequestParam(value = "name", defaultValue = "World") String name) throws IOException {
    String json = "{\"name\":\""+name+"\"}";
    ObjectMapper om = new ObjectMapper();
    Map data = om.readValue(json, Map.class);
    return ImmutableMap.<String, Object>builder()
        .put("data", data)
        .put("status", "200")
        .put("id", counter.incrementAndGet())
        .put("message", String.format(template, name))
        .build();
  }

  @RequestMapping("/greeting-jackson")
  public void greetingJackson(@RequestParam(value = "name", defaultValue = "World") String name) throws IOException {
    String json = "{\"name\":\""+name+"\"}";
    ObjectMapper om = new ObjectMapper();
    Map data = om.readValue(json, Map.class);
  }

  @RequestMapping("/greeting-gson")
  public void greetingGson(@RequestParam(value = "name", defaultValue = "World") String name) throws IOException {
    String json = "{\"name\":\""+name+"\"}";
    Gson gson = new Gson();
    Map data = gson.fromJson(json, Map.class);
  }

  @RequestMapping("/greeting-map-1")
  public Map<String, Object> greetingMap1(@RequestParam(value = "name", defaultValue = "World") String name) throws IOException {
    return ImmutableMap.<String, Object>builder()
        .put("status", "200")
        .put("id", counter.incrementAndGet())
        .put("message", String.format(template, name))
        .build();
  }

  @RequestMapping("/greeting-fast")
  @ResponseBody
  public String greetingFast(@RequestParam(value = "name", defaultValue = "World") String name) {
    return getPrimes(100000).size() + ", " + String.format(template, name);
  }

  public List<Integer> getPrimes(int max){
    List<Integer> primes = new ArrayList<Integer>();
    primes.add(2);
    primes.add(3);
    for(int i=5;i<=max;i+=2){
      int t = (int) Math.sqrt(i);
      boolean isPrime = true;
      for(int j=1;j<primes.size()&&primes.get(j)<=t;j++){
        if(i%primes.get(j)==0){
          isPrime = false;
          break;
        }
      }
      if(isPrime)primes.add(i);
    }
    return primes;
  }

}
