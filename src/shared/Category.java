package shared;

public class Category {
	private String categoryName;
	private String categoryId;
	
	public Category(String categoryId) {
		this.categoryId = categoryId;
		this.categoryName = generateCategoryName(categoryId);
	}
	
	public Category(String categoryName, String categoryId) {
		this.categoryName = categoryName;
		this.categoryId = categoryId;
	}
	
	private static String generateCategoryName(String categoryId) {
		return categoryId.replace("_u_", " & ").replace("_", " ");
	}
	
	public String getCategoryName() {
		return categoryName;
	}

	public String getCategoryId() {
		return categoryId;
	}
}
