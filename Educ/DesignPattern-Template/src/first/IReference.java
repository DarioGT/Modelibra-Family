package first;

public interface IReference {
	
	public boolean add(String code, String name);
	
	public String getName(String code);
	
	public int size();
	
	public boolean empty();
	
	public boolean contain(String code);

}
