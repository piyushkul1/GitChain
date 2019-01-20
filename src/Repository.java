import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.1.0.
 */
public class Repository extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50610420806100206000396000f30060806040526004361061006c5763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416632b54a9b78114610071578063a0fc76fa146100a7578063b208e55d146100d9578063d840f66f146100f1578063de6cb14c14610155575b600080fd5b34801561007d57600080fd5b506100896004356101ba565b60408051938452602084019290925282820152519081900360600190f35b3480156100b357600080fd5b506100c56004356024356044356101d6565b604080519115158252519081900360200190f35b3480156100e557600080fd5b506100c56004356102da565b3480156100fd57600080fd5b50610109600435610325565b6040805196875260208701959095528585019390935273ffffffffffffffffffffffffffffffffffffffff909116606085015215156080840152151560a0830152519081900360c00190f35b34801561016157600080fd5b5061016a61039b565b60408051602080825283518183015283519192839290830191858101910280838360005b838110156101a657818101518382015260200161018e565b505050509050019250505060405180910390f35b6000908152602081905260409020805460019091015490918190565b6000838152602081905260408120600301547501000000000000000000000000000000000000000000900460ff1615156001141561021357600080fd5b50600083815260208190526040812084815560018082019490945560028101929092556003909101805475010000000000000000000000000000000000000000003373ffffffffffffffffffffffffffffffffffffffff199092169190911775ff00000000000000000000000000000000000000000019161774ff00000000000000000000000000000000000000001916905581548083018355908290527fb10e2d527612073b26eecdfd717e6a320cf44b4afac2b0732d9fcbe2b7fa0cf6019190915590565b6000818152602081905260409020600301805474ff00000000000000000000000000000000000000001916740100000000000000000000000000000000000000001790556001919050565b60006020819052908152604090208054600182015460028301546003909301549192909173ffffffffffffffffffffffffffffffffffffffff81169060ff740100000000000000000000000000000000000000008204811691750100000000000000000000000000000000000000000090041686565b606060018054806020026020016040519081016040528092919081815260200182805480156103ea57602002820191906000526020600020905b815481526001909101906020018083116103d5575b50505050509050905600a165627a7a72305820915761826b4706f708fedf9d0d31852542877537d46e9edeece4a6eff87721fa0029";

    public static final String FUNC_RETRIEVEHASH = "RetrieveHash";

    public static final String FUNC_UPLOADFILE = "UploadFile";

    public static final String FUNC_DELETEFILE = "DeleteFile";

    public static final String FUNC_FILESTRUCTS = "fileStructs";

    public static final String FUNC_GETKEYARRAY = "getKeyArray";

    @Deprecated
    protected Repository(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Repository(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Repository(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Repository(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<Tuple3<byte[], byte[], byte[]>> RetrieveHash(byte[] fileVer) {
        final Function function = new Function(FUNC_RETRIEVEHASH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(fileVer)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
        return new RemoteCall<Tuple3<byte[], byte[], byte[]>>(
                new Callable<Tuple3<byte[], byte[], byte[]>>() {
                    @Override
                    public Tuple3<byte[], byte[], byte[]> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<byte[], byte[], byte[]>(
                                (byte[]) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue(), 
                                (byte[]) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> UploadFile(byte[] fileVer, byte[] hash1, byte[] hash2) {
        final Function function = new Function(
                FUNC_UPLOADFILE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(fileVer), 
                new org.web3j.abi.datatypes.generated.Bytes32(hash1), 
                new org.web3j.abi.datatypes.generated.Bytes32(hash2)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> DeleteFile(byte[] fileVer) {
        final Function function = new Function(
                FUNC_DELETEFILE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(fileVer)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple6<byte[], byte[], byte[], String, Boolean, Boolean>> fileStructs(byte[] param0) {
        final Function function = new Function(FUNC_FILESTRUCTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}));
        return new RemoteCall<Tuple6<byte[], byte[], byte[], String, Boolean, Boolean>>(
                new Callable<Tuple6<byte[], byte[], byte[], String, Boolean, Boolean>>() {
                    @Override
                    public Tuple6<byte[], byte[], byte[], String, Boolean, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple6<byte[], byte[], byte[], String, Boolean, Boolean>(
                                (byte[]) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue(), 
                                (byte[]) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (Boolean) results.get(4).getValue(), 
                                (Boolean) results.get(5).getValue());
                    }
                });
    }

    public RemoteCall<List> getKeyArray() {
        final Function function = new Function(FUNC_GETKEYARRAY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Bytes32>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    @Deprecated
    public static Repository load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Repository(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Repository load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Repository(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Repository load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Repository(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Repository load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Repository(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Repository> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Repository.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Repository> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Repository.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Repository> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Repository.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Repository> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Repository.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
