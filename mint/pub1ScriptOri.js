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

async function check_whitelist(){
    const myContract = new caver.klay.Contract(ABI, CONTRACTADDRESS);

    if(!account){
        alert("error : Wallet connect please!");
        return;
    }

    await myContract.methods.isWhiteList(account).call()
    .then(function (result){

    })
    .catch(function (error){

    })
}

async function publicMint() {
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
    const amount = document.getElementById('amount').value;
    await check_status();
    
    if (maxSaleAmount + 1 <= mintIndexForSale) {
        alert("Out of Nyangs!");
        return;
    } else if (blockNumber <= mintStartBlockNumber) {
        alert("VOGONYANGS IS COMMING!");
        return;
    }
    
    const total_value = BigNumber(amount * mintPrice);

    let estmated_gas;

    await myContract.methods.publicMint(amount)
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