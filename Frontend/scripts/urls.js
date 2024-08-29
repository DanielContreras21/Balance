<<<<<<< HEAD
const PRODUCTION = false

=======
const PRODUCTION = true
>>>>>>> 7bda02705952e1f3be2ce745e78afba6e90e7b26
function backendUrl(){
    if(PRODUCTION){
        return "https://balance-tests.up.railway.app"
    } else{
        return "http://localhost:8080"
    }
}
