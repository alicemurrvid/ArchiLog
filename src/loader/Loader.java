package loader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * Loader
 * @author amurrayvidal, paudiger, mschneider
 *
 */
public class Loader {
	public static Loader getInstance() {
		return LazyLoader.instance;
	}
	
	private static class LazyLoader {
		private static final Loader instance = new Loader();
	}
		
	public Object getPlugin(PluginDescriptor descr){
		try {
			return Class.forName(descr.path).getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<String> getDescription(){
		ArrayList<String> descr = new ArrayList<String>();
		
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader("src/data/config.json")){
			JSONObject obj = (JSONObject) jsonParser.parse(reader);
			descr.add(obj.toJSONString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return descr;
	}
	
	public String getPathLoader(String categorie, String type) throws FileNotFoundException, IOException, ParseException {
		JSONParser jsonParser = new JSONParser();
		String path = " ";
		try (FileReader reader = new FileReader("src/data/config.json")){
			JSONObject cate = (JSONObject) ((JSONObject) jsonParser.parse(reader)).get(categorie);
			JSONObject obj = (JSONObject) cate.get(type);
			path = obj.get("path").toString();
			
		} 
		return path;
	}
	
	public List<PluginDescriptor> getDescriptionFor(String category){
		List<PluginDescriptor> list = new ArrayList<PluginDescriptor>();
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader("src/data/config.json")){
			JSONObject cate = (JSONObject) ((JSONObject) jsonParser.parse(reader)).get(category);
			@SuppressWarnings("unchecked")
			Set<String> keys = cate.keySet();
			for (String key : keys) {
				list.add( new PluginDescriptor(
						(String)((JSONObject) cate.get(key)).get("name"),
						(String)((JSONObject) cate.get(key)).get("path"),
						(String)((JSONObject) cate.get(key)).get("description"),
						(boolean)((JSONObject) cate.get(key)).get("autorun"),
						new String("")));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<List<PluginDescriptor>> getTypeFor(String category){
		List<List<PluginDescriptor>> list = new ArrayList<>();
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader("src/data/config.json")){
			JSONObject cate = (JSONObject) ((JSONObject) jsonParser.parse(reader)).get(category);
			Set<String> types = cate.keySet();
			for (String type1 : types) {
				JSONObject typeObj = (JSONObject) cate.get(type1);
				Set<String> keys = typeObj.keySet();
				List<PluginDescriptor> listPlugin = new ArrayList<>();
				for (String key : keys) {
					listPlugin.add( new PluginDescriptor(
							(String)((JSONObject) typeObj.get(key)).get("name"),
							(String)((JSONObject) typeObj.get(key)).get("path"),
							(String)((JSONObject) typeObj.get(key)).get("description"),
							(boolean)((JSONObject) typeObj.get(key)).get("autorun"),
							type1));
				}
				list.add(listPlugin);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return list;
	}
}
