import java.io.File;
import java.io.IOException;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multiaddr.MultiAddress;
import io.ipfs.multihash.Multihash;

public class IPFSUtil {

	public static String uploadToIPFS(String file) throws Exception {
		IPFS ipfs = new IPFS(new MultiAddress("/ip4/127.0.0.1/tcp/5001"));
		NamedStreamable namedStream = new NamedStreamable.FileWrapper(new File(file));
		MerkleNode addResult = ipfs.add(namedStream).get(0);
		Multihash pointer = addResult.hash;
		System.out.println("\nFile uploaded to IPFS. File hash:: " + pointer.toString());
		return pointer.toString();
	}

	public static byte[] readFileFromIPFS(String hash) throws IOException {
		IPFS ipfs = new IPFS(new MultiAddress("/ip4/127.0.0.1/tcp/5001"));
		Multihash filePointer = Multihash.fromBase58(hash);
		byte[] fileContents = ipfs.cat(filePointer);
		return fileContents;
	}
}
