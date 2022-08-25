
function Subconnect(account, balance) {
	
    document.getElementById("myWallet").innerHTML = `지갑주소: ${account}`
    var waddr = `${account}`;
    if (waddr.length > 10) {
    	waddr = waddr.substr(0, 6) + '...' + waddr.substr(36).toUpperCase();;
  	}
    document.getElementById("myWallet2").innerHTML = waddr;

    document.getElementById("myKlay").innerHTML = `잔액: ${caver.utils.fromPeb(balance, "KLAY")} KLAY`
	    
}

function Substatus(result) {
	
    mintIndexForSale = parseInt(result[1]);
    mintLimitPerBlock = parseInt(result[2]);
    mintStartBlockNumber = parseInt(result[4]);
    mintWhiteListStartBlockNumber = parseInt(result[5]);
    maxSaleAmount = parseInt(result[6]);
    mintPrice = parseInt(BigNumber(result[7]));
    mintPublicSaleAmount = parseInt(result[9]);
    mintPublicSaleIndex  = parseInt(result[10]); 
    
    document.getElementById("mintCnt").innerHTML = `${mintPublicSaleIndex - 1 } / ${mintPublicSaleAmount}`;
    document.getElementById("mintLimitPerBlock").innerHTML = `트랜잭션당 최대 수량: ${mintLimitPerBlock}개`;
    document.getElementById('amount').max = mintLimitPerBlock;
    document.getElementById("mintStartBlockNumber").innerHTML = `민팅 시작 블록: #${mintStartBlockNumber}`;
    document.getElementById("mintPrice").innerHTML = `민팅 가격: ${caver.utils.fromPeb(mintPrice, "KLAY")} KLAY`;
       
}

