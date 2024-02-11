package loader;

/**
 * PluginDescriptor
 * @author amurrayvidal, paudiger
 */
public class PluginDescriptor {
	String path;
	public String name;
	public String description;
	public boolean autorun;
	String type;
	
	public PluginDescriptor(String name, String path, String description, boolean autorun, String type) {
		this.path = path;
		this.name = name;
		this.description = description;
		this.autorun = autorun;
		this.type = type;
	}

	
	public String getType() {
		return type;
	}


	@Override
	public String toString() {
		return "PluginDescriptor [path=" + path + ", name=" + name + ", description=" + description + 
				", type=" + type +", autorun=" + autorun + "]";
	}
	
	
}
