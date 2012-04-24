import java.io.IOException;

public class TranslatorTester {
	public static void main(String args[]) {
		EsacTranslator et = new EsacTranslator();
		try {
			System.out.println(et.getMelody());
		} catch (IOException ioe) {
			System.out.println("Something went wrong.");
		}
	}
}