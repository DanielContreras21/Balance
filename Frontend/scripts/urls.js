const PRODUCTION = true

function backendUrl(){
    if(PRODUCTION){
        return "https://balance-tests.up.railway.app"
    } else{
        return "http://localhost:8080"
    }
}
