import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
	protected String email;
	protected String password;
	protected String login;
	protected boolean premium;
	protected String credit;
	static User user;
	static List<User> users = new ArrayList<User>();
	List<Product> userProduct = new ArrayList<Product>();
	
	User(){
		email=null;
		password=null;
		login=null;
		premium=false;
		credit = null;
		user = this;
	}
	User(String email, String password,String login,String credit,boolean premium){
		this.email = email;
		this.password = password;
		this.login = login;
		this.premium =premium;
		this.credit= credit;
		Configuration.write(login, email, password);
		user = this;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean getPremium() {
		return premium;
	}
	 public void setPremium(boolean premium) {
		this.premium = premium;
	}
	public String getCredir() {
		return credit;
	}
	public void setCredit(String credir) {
		this.credit=credit;
	}
	static void sign_up(){
		String email;
		String login;
		String password;
		String credit = null;
		boolean premium;
		do{
		Scanner tec = new Scanner(System.in);
		System.out.println("Indroduce tu correo");
		email=tec.nextLine();
		System.out.println("Indroduce tu usuario(con más de 7 caracteres)");
		login=tec.nextLine();
		System.out.println("Indroduce tu contraseña(con más de 7 caracteres)");
		password=tec.nextLine();
		System.out.println("Quiere ser premium?(true/false)");
		premium=tec.nextBoolean();
		if(premium ==true){
			Scanner tecs = new Scanner(System.in);
			System.out.println("Introduza su tarjeta de crédito");
			credit=tecs.nextLine();
		}
		else{
			System.out.println("Más adelante puede ser premium");	
		}
		}while(error(login, email, password, credit));
		System.out.println("Bienvenido!!");
		User usuario = new User(email,password,login,credit,premium);
	}
	static boolean error(String login, String email, String password, String credit){
		boolean aux=false;
		String emailPattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" +
			      "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
		Pattern pattern = Pattern.compile(emailPattern);
		if (email != null) {
			   Matcher matcher = pattern.matcher(email);
			   if (!matcher.matches()) {
			     System.out.println("Error");
			     aux=true;
			  }
		}
		if(password.length()<7){
			System.out.println("Error");
			aux= true;
		}
		if(login.length()<7){
			System.out.println("Error");
			aux=true;
		}
		if(credit!=null)
			if(credit.length()<10 ){
			System.out.println("Error");
			aux=true;
		}
		return aux;
	}
void buy(Product product){
		if(product.stock > 0){
			product.stock --;
		}
		product.mailPlus(product.price);
		finalPrice(product);
		System.out.println("RESUMEN");
		System.out.println("Nombre:" + product.name );
		System.out.print("Precio:");
		System.out.printf("%.2f", product.price);
		System.out.println(product.getMny().toString());
}
	void finalPrice(Product product){
		if (!getPremium()){
			product.price += product.mailPlus(product.price);	
		}
	}
	void printUserProducts(){
		for (int i = 0; i < userProduct.size(); i++){
			System.out.println(userProduct.get(i).getName());
		}
	}
}
