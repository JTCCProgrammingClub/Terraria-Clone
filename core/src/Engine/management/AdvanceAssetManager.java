package Engine.management;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.assets.AssetManager;

public class AdvanceAssetManager extends AssetManager {
	
	private static AdvanceAssetManager instance = null;
	
	/**
	 * First string is the short name and the second
	 * AssetFile contains the original name and class of the asset
	 */
	private Map<String, AssetFile> assets;
	
	protected AdvanceAssetManager() {
		assets = new HashMap<String, AssetFile>();
	}
	
	public static AdvanceAssetManager getInstance() {
		if(instance == null)
			instance = new AdvanceAssetManager();
		
		return instance;
	}
	
	public <T> void loadByShort(String originalAssetName, String shortAssetName, Class<T> type) {
		// make sure the asset is not already loaded
		if(assets.containsKey(originalAssetName) == false) {
			// store asset
			assets.put(shortAssetName, new AssetFile(originalAssetName, type));
			load(originalAssetName, type); // load asset
		}
			
	}
	
	public <T> T getByShort(String shortAssetName, Class<T> type) {
		return get(assets.get(shortAssetName).getAssetName(), type);
	}
	
	public String[] getShortAssetNames() {
		return assets.keySet().toArray(new String[assets.size()]);
	}
	
	public String getShortAssetName(String originalAssetName) {
		String shortAssetName = "";
		
		for(Map.Entry<String, AssetFile> assetMap : assets.entrySet()) {
			if(assetMap.getValue().getAssetName().equals(originalAssetName)) {
				shortAssetName = assetMap.getKey();
				break;
			}
		}
		
		return shortAssetName;
	}
	
	public void unloadByShort(String shortAssetName) {
		// check if it exist
		if(assets.containsKey(shortAssetName) == true) {
			super.unload(assets.get(shortAssetName).getAssetName());
			assets.remove(shortAssetName);
		}
	}
	
	@Override
	public void unload(String fileName) {
		// remove file from assets
		assets.remove(getShortAssetName(fileName));
		super.unload(fileName);
	}
	
}

class AssetFile{
	
	private Class<?> assetClass;
	private String fileName;
	
	public AssetFile(String fileName, Class<?> assetClass) {
		this.fileName = fileName;
		this.assetClass = assetClass;
	}
	
	public Class<?> getAssetClass() {
		return assetClass;
	}
	
	public String getAssetName() {
		return fileName;
	}
	
}
