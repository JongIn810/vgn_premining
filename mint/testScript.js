let account;
let mintIndexForSale = 0;
let maxSaleAmount = 0;
let mintPrice = 0;
let mintStartBlockNumber = 0;
let mintLimitPerBlock = 0;

let blockNumber = 0;
let blockCnt = false;

function cntBlockNumber() {
    if(!blockCnt) {
        setInterval(function(){
            blockNumber+=1;
            document.getElementById("blockNubmer").innerHTML = "Now Block : #" + blockNumber;
        }, 1000);
        blockCnt = true;
    }
}

async function connect() {
    const accounts = await klaytn.enable();
    if (klaytn.networkVersion === 8217) {

    } else if (klaytn.networkVersion === 1001) {

    } else {
        alert("ERROR : Do not connect Klaytn Network!");
        return;
    }
    account = accounts[0];
    caver.klay.getBalance(account)
        .then(function (balance) {
        	
        	Subconnect(account, balance)
        	
        });
    await check_status();
}

async function check_status() {
    const myContract = new caver.klay.Contract(ABI, CONTRACTADDRESS);
    await myContract.methods.mintingInformation().call()
        .then(function (result) {
            
            Substatus(result)
        })
        .catch(function (error) {

        });
    blockNumber = await caver.klay.getBlockNumber();
    document.getElementById("blockNubmer").innerHTML = "Now Block : #" + blockNumber;
    cntBlockNumber();
}

async function transfer() {
    if (klaytn.networkVersion === 8217) {
     //   console.log("Mainnet Cypress");
    } else if (klaytn.networkVersion === 1001) {
     //   console.log("Testnet Baobob");
    } else {
        alert("ERROR : Do not connect Klaytn Network!");
        return;
    }
    if (!account) {
        alert("ERROR : Connect wallet first!");
        return;
    }

    const myContract = new caver.klay.Contract(ABI, CONTRACTADDRESS);
    
    await check_status();
    
    let estmated_gas;

    /*
     * 
     * 	{
		"constant": false,
		"inputs": [
			{
				"internalType": "address",
				"name": "from",
				"type": "address"
			},
			{
				"internalType": "address",
				"name": "to",
				"type": "address"
			},
			{
				"internalType": "uint256",
				"name": "tokenId",
				"type": "uint256"
			}
		],
		"name": "transferFrom",
		"outputs": [],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "function"
	},
     * 
     * 
     */
    
    
    
    await myContract.methods.transferFrom(fromAddr, toAddr, tknId)
        .estimateGas({
            from: account,
            gas: 6000000,
            value: total_value
        })
        .then(function (gasAmount) {
            estmated_gas = gasAmount;
            myContract.methods.publicMint(amount)
                .send({
                    from: account,
                    gas: estmated_gas,
                    value: total_value
                })
                .then(function(receipt){
                    if(receipt.status)
                    {
                        let mintCount = receipt.events.Transfer.length;

                        if( mintCount == undefined)
                        {
                            alert('Congratulation! Your 1 Nyangs has been minted Successfully!');
                        
                        }
                        else
                        {
                            alert('Congratulation! Your ' +  mintCount + ' Nyangs has been minted Successfully!');
                        
                        }
                        check_status();
                    }
                    else
                    {
                        alert('Sorry! Minting Failed.');
                    }
                });
        })
        .catch(function (error) {
            alert("Sorry! Minting Failed.");
        });
}


