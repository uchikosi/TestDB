public class Test {
	public static void main(String[] args) {
		TestUserDAO dao = new TestUserDAO();
		dao.select("taro", "123");
//		指定されたnameとpasswordの行を取得している

		dao.selectAll();
//		allなのですべて取得する

		dao.selectByName("taro");
//		指定されたnameの行を取得している

		dao.selectByPassword("123");
//		指定されたpasswordの行を取得している

		dao.updateUserNameByUserName("taro", "saburo");
//		user_name （taro）が指定された値と一致する行を更新(saburo)する

		dao.insert(4, "shiro", "012");
//		user_id、name、passwordカラムに新しい行を登録

		dao.delete("jiro");
//		user_name が指定された値と一致する行を削除
	}
}