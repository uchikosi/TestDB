
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//JDBCを使用する際に必要なクラスをプログラムに組み込むためのものです。データベースとの接続やクエリの実行にはこれらのクラスが必要となります。

public class DBConnector {
	private static String driverName = "com.mysql.jdbc.Driver";
	//JDBCドライバー（JDBCとデータベースとの通信を取り持つためのライブラリ）の名前を変数に代入している
	private static String url ="jdbc:mysql://localhost:8889/testdb?autoReconnect=true&useSSL=false";
	private static String user = "root";
	private static String password = "root";
	public Connection getConnection() {
//		接続状態にする
		Connection con = null;
//		一度状態を初期化にしています。この二つは接続しかしないクラス。
		try{
//			try～catchは Java の例外処理の為の構文、中にはエラーが発生しそうな処理を書きます。
			Class.forName(driverName);
//			指定されたJDBCドライバーをロードします。
			con = DriverManager.getConnection(url,user,password);
//			url で指定されたデータベースに、user と password を使用して接続します。
//			DriverManager.getConnection メソッドは、指定されたURL、ユーザー名、パスワードでデータベースに接続するための Connection オブジェクトを返す。
		} catch (ClassNotFoundException e) {
			e.printStackTrace() ;
		} catch (SQLException e) {
			e.printStackTrace() ;
//			tryの中でエラーが発生した場合に、catchが受け取り、printStackTraceでエラーに至る
//			履歴を表示してくれます。間違った際の赤い文字です。今回だと２つのエラーが表示されます。
//			ClassNotFoundException（クラスが見つからない場合の例外）と SQLException（データベ
//			ース処理に関する例外）です
		}
		return con ;
	}
}
//JDBCドライバー（JDBCとデータベースとの通信を取り持つためのライブラリ）の名前を変数に代入している
//jdbc:mysql://: JDBC ドライバーが MySQL データベースと通信するための部分です。
//localhost:8889: データベースサーバーのホスト名とポート番号、今回は、データベースサーバーが同じコンピューター上にあり、ポート番号は8889
///testdb: データベースの名前です。この例では testdb という名前のデータベースに接続
//?autoReconnect=true&useSSL=false: これは接続オプションを指定する部分です。
//autoReconnect=true は、接続が失われた場合に自動的に再接続を試みることを指定しています。
//useSSL=false は SSL を使用しないように指定しています。
//SSL（Secure Sockets Layer）は、セキュアな通信を確立するための暗号化プロトコルです。
//SSLは、通信するデータを暗号化し、データの送受信者を認証することで、セキュリティを向上させます。