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
        
        alert("You are whitelisted. You can buy " + result + " Nyangs.");
    })
    .catch(function (error){
        
        alert("Sorry! You are not whitelisted.");
    })
}

