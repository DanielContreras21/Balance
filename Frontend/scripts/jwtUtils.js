 function parseJwt(token) {
     try {
         const base64Url = token.split('.')[1]
         const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/')
         const jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
             return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2)
         }).join(''))

         return JSON.parse(jsonPayload)
     } catch (e) {
         return null
     }
 }

 function isJwtValid(token) {
     if (!token) return false

     const decodedToken = parseJwt(token)
     if (!decodedToken) return false

     const currentTime = Math.floor(Date.now() / 1000)
     return decodedToken.exp && decodedToken.exp > currentTime
 }

 if (!JWT || !isJwtValid(JWT)) {
     location.href = '/templates/components/auth/login.html'
 }
