package executable;

public class SigCalculator {

	public static void main(String[] args) {
		ExternalFiles externalFiles = new ExternalFiles();
		try{
		externalFiles.RunTheApp(args);
		}
		catch(Exception ex){
			System.out.println("The file was not found!");
			ex.printStackTrace();
		}
	}

}
