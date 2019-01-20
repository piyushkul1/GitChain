import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;
import org.web3j.utils.Numeric;

public class GitChain {

	public static List<String> getAllFiles() throws Exception {
		Web3j web3 = Web3j.build(new HttpService(Constants.INFURA_URL));

		Credentials credentials = WalletUtils.loadCredentials(Constants.PASSWORD,
				Constants.WALLET_DIR + Constants.WALLET_FILE);
		Repository contract = getContract(web3, credentials);
		List<byte[]> l = contract.getKeyArray().send();
		List<String> files = new ArrayList<String>();
		for (byte[] b : l) {
			files.add(byteToString(b));
		}
		return files;
	}

	public static void uploadFile(String fileVer, String hash) throws Exception {
		Web3j web3 = Web3j.build(new HttpService(Constants.INFURA_URL));

		Credentials credentials = WalletUtils.loadCredentials(Constants.PASSWORD,
				Constants.WALLET_DIR + Constants.WALLET_FILE);
		Repository contract = getContract(web3, credentials);
		System.out.println("Transaction hash:: " + contract.UploadFile(stringToByte(fileVer),
				stringToByte(hash.substring(0, 23)), stringToByte(hash.substring(23, 46))).send().getTransactionHash());

	}

	public static HashMap<String, Integer> getLatestFiles() throws Exception {
		List<String> l = new ArrayList<String>();
		List<String> files = getAllFiles();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (String file : files) {
			String[] f = file.split("@");
			String fileName = f[0];
			Integer version = Integer.parseInt(f[1]);
			if (map.containsKey(f[0])) {
				if (version > map.get(fileName)) {
					map.put(fileName, version);
				}
			} else {
				map.put(fileName, version);
			}
		}
		return map;
	}

	public static byte[] stringToByte(String s) {
		return Numeric.hexStringToByteArray(asciiToHex(s));
	}

	public static String byteToString(byte[] b) {
		return hexToASCII(Numeric.toHexStringNoPrefix(b));
	}

	public static String asciiToHex(String asciiValue) {
		char[] chars = asciiValue.toCharArray();
		StringBuffer hex = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			hex.append(Integer.toHexString((int) chars[i]));
		}
		return hex.toString() + String.join("", Collections.nCopies(32 - (hex.length() / 2), "00"));
	}

	public static Repository getContract(Web3j web3, Credentials credentials) {
		return Repository.load(InputVal.contractAddr, web3, credentials, ManagedTransaction.GAS_PRICE,
				Contract.GAS_LIMIT);
	}

	public static String hexToASCII(String hexValue) {
		StringBuilder output = new StringBuilder("");
		for (int i = 0; i < hexValue.length(); i += 2) {
			String str = hexValue.substring(i, i + 2);
			output.append((char) Integer.parseInt(str, 16));
		}
		return output.toString().trim();
	}

	public static String retrieveHash(String fileVer) throws Exception {
		Web3j web3 = Web3j.build(new HttpService(Constants.INFURA_URL));
		Credentials credentials = WalletUtils.loadCredentials(Constants.PASSWORD,
				Constants.WALLET_DIR + Constants.WALLET_FILE);
		Repository contract = getContract(web3, credentials);
		Tuple3<byte[], byte[], byte[]> tuple = contract.RetrieveHash(stringToByte(fileVer)).send();
		return byteToString(tuple.getValue2()) + byteToString(tuple.getValue3());

	}

}
