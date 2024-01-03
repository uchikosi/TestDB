
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//JDBCを使用する際に必要なクラスをプログラムに組み込むためのものです。データベースとの接続やクエリの実行にはこれらのクラスが必要となります。
public class TestUserDAO {
	String name = "";
	String password = "";
	public void select(String name,String password) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
//		DB への接続の準備。DBと会話する為のコード。これで mysqlにログイン出来る。
		String sql ="select * from test_table where user_name=? and password=?";
//		user_name=? password=?に入る２つのカラム条件を満たしたデータが sqlに代入されます。
//		?はプレースホルダ、その都度違うデータを入れていきたい時に使用。
		try {
				PreparedStatement ps = con.prepareStatement(sql);
//				PreparedStatementとは DB まで運んでくれる箱です。
				ps.setString(1, name);
				ps.setString (2, password);
				ResultSet rs=ps.executeQuery();
//				executeQuery();は実行メソッドで、必ず ResultSetが返ってきます。
				if (rs.next()) {
//					if (rs.next()) { ... } は、ResultSet オブジェクトが持つ結果セットの次の行に移動し、
//					その行が存在する場合にブロック内の処理を実行する条件文です。
//					next() メソッドは、行が存在する場合に true を返し、行が存在しない場合に false を返します。
					System.out.println(rs.getString("user_name"));
					System.out.println(rs.getString("password"));
				}
		}
		catch (SQLException e ) {
			e.printStackTrace();
		}
		try {
			con.close() ;
//			con.close()　データベースとの接続を終了させるという意味です。
		}
		catch (SQLException e ) {
			e.printStackTrace();
//			tryの中でエラーが発生した場合に、catchが受け取り、printStackTraceでエラーに至る
//			履歴を表示してくれます。間違った際の赤い文字です。今回だと２つのエラーが表示されます。
//			ClassNotFoundException（クラスが見つからない場合の例外）と SQLException（データベ
//			ース処理に関する例外）です
		}
	}

	public void selectAll() {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		String sql ="select * from test_table";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("user_name"));
				System.out.println(rs.getString("password"));
			}
		}
		catch (SQLException e ) {
			e.printStackTrace();
		}
		try {
			con.close() ;
		}
		catch (SQLException e ) {
			e.printStackTrace();
		}
	}
//	user_name、paswordカラムをMysqlから取得して表示
//	while (rs.next())でデータがなくなるなでループしてすべてのデータを表示する

	public void selectByName(String name) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		String sql ="select * from test_table where user_name=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("user_name"));
				System.out.println(rs.getString("password"));
			}
		}
		catch (SQLException e ) {
			e.printStackTrace();
		}
		try {
			con.close() ;
		}
		catch (SQLException e ) {
			e.printStackTrace();
		}
	}
//	テーブルから指定されたユーザー名に一致する行を取得する。
//	willをしているが今回はセレクトしたもののみ取得してるためps.setString(1, name);の
//	user_name、paswordカラムの値のみ表示される

	public void selectByPassword(String password) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		String sql ="select * from test_table where password=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString (1, password);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("user_name"));
				System.out.println(rs.getString("password"));
			}
		}
		catch (SQLException e ) {
			e.printStackTrace();
		}
		try {
			con.close() ;
		}
		catch (SQLException e ) {
			e.printStackTrace();
		}
	}
//	テーブルから指定されたパスワードに一致する行を取得する。
//	willをしているが今回はセレクトしたもののみ取得してる
//	user_name、paswordカラムの値のみ表示される

	public void updateUserNameByUserName(String oldName,String newName) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		String sql ="update test_table set user_name=? where user_name=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, newName);
			ps.setString (2, oldName);
			int i=ps.executeUpdate();
			if (i>0) {
				System.out.println(i + "件更新されました");
			}
			else{
				System.out.println("該当するデータはありませんでした");
			}
		}
		catch (SQLException e ) {
			e.printStackTrace();
			}
			try {
				con.close() ;
			}
			catch (SQLException e ) {
				e.printStackTrace();
		}
	}
//	指定されたユーザー名（oldName）に一致する行のユーザー名を新しいユーザー名（newName）に更新するメソッド
//	ifでexecuteUpdate メソッドは、実行された更新により影響を受けた行の数を返します。
//	この値が1より大きい場合は、指定されたユーザー名に一致する行が存在し、ユーザー名が更新され、更新された件数が表示される。

	public void insert(int user_id,String name, String password) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		String sql ="insert into test_table values(?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, user_id);
			ps.setString(2, name);
			ps.setString (3, password);
			int i=ps.executeUpdate();
			if (i>0) {
				System.out.println(i + "件登録されました");
			}
		}
		catch (SQLException e ) {
			e.printStackTrace();
		}
		try {
			con.close() ;
		}
		catch (SQLException e ) {
			e.printStackTrace();
		}
	}
//	 test_table のuser_id、name、passwordカラムに新しい行を挿入するためのメソッド
//	ifでexecuteUpdate メソッドは、実行された更新により影響を受けた行の数を返します。
//	この値が1より大きい場合は、指定されたユーザー名に一致する行が存在し、ユーザー名が更新され、新たに登録された件数が表示される。

	public void delete(String name) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		String sql ="delete from test_table where user_name=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			int i=ps.executeUpdate();
			if (i>0) {
				System.out.println(i + "件削除されました");
			}else{
				System.out.println("該当する削除データはありませんでした");
			}
		}
		catch (SQLException e ) {
			e.printStackTrace();
		}
		try {
			con.close() ;
		}
		catch (SQLException e ) {
			e.printStackTrace();
		}
	}
}
//user_nameに一致するデータを test_table テーブルから削除するためのメソッド
//ifでexecuteUpdate メソッドは、実行された削除により影響を受けた行の数を返します。
//この値が1より大きい場合は、指定されたユーザー名に一致する行が存在し、ユーザー名が削除され、新たに登録された件数が表示される。

//DAOは「DataAccessObject（データアクセスオブジェクト）」、データベースや永続化されたデータへのアクセスを提供するためのオブジェクト指向のパターンです。
//DAOの流れについては基本的に決まっています。
//DAOは DBConnectorからインスタンス化をして、getConnectionを呼びだして、mysqlにロ
//グインをします。その後は SQL文を書いて PreparedStatment の中にデータを入れて
//executeQueryもしくは updateQueryで実行して con.close をして接続を切ります。