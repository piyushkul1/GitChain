import java.util.Scanner;

public class App {

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		System.out.println(Constants.ASTERIX);
		System.out.println(Constants.GITCHAIN);
		System.out.println(Constants.ASTERIX + "\n");
		System.out.println("Please enter the contract address for GitChain::");
		InputVal.contractAddr = sc.nextLine();
		System.out.println("Set default directory for GitChain::");
		InputVal.dir = sc.nextLine() + "\\";
		System.out.println("Default directory updated.");

		while (true) {
			showMenu();
			String option = sc.nextLine();
			if (option.equals(InputVal.QUIT)) {
				System.out.println(Constants.GO_GATORS);
				break;
			} else if (option.equals(InputVal.CD)) {
				System.out.println("Set default directory for GitChain::");
				InputVal.dir = sc.nextLine() + "\\";
				System.out.println("Default directory updated.");
			} else if (option.startsWith(InputVal.GITC)) {
				String[] input = option.split(" ");
				switch (input[1]) {
				case InputVal.INIT:
					GitCLI.init(InputVal.dir);
					break;
				case InputVal.ADD:
					GitCLI.add(InputVal.dir);
					break;
				case InputVal.PUSH:
					GitCLI.push(InputVal.dir);
					break;
				case InputVal.CLONE:
					GitCLI.clone(InputVal.dir);
					break;
				case InputVal.PULL:
					GitCLI.pull(InputVal.dir);
					break;
				default:
					break;
				}
			}
		}
		sc.close();
	}

	public static void showMenu() {
		System.out.println("\n" + Constants.ASTERIX + Constants.ASTERIX);
		System.out.println("Usage: gitc <command>\n");
		System.out.format("%-8s%16s%n", InputVal.INIT,
				"Create an empty Git repository or reinitialize an existing one");
		System.out.format("%-8s%16s%n", InputVal.ADD, "Add file contents to the index");
		System.out.format("%-8s%16s%n", InputVal.PUSH, "Update remote refs along with associated objects");
		System.out.format("%-8s%16s%n", InputVal.CLONE, "Clone a repository into a new directory");
		System.out.format("%-8s%16s%n", InputVal.PULL,
				"Fetch from and integrate with another repository or a local branch\n\n");
		System.out.println("Type cd to change default directory for GitChain.");
		System.out.println("Type exit to quit from GitChain.\n");
		System.out.println("Current directory:: " + InputVal.dir);

		System.out.println(Constants.ASTERIX + Constants.ASTERIX + "\n");
	}
}
