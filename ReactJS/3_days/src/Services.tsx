import axios from "axios";

const userName = process.env.REACT_APP_USERNAME;
const password = process.env.REACT_APP_PASSWORD;
console.log(`userName`, userName)
console.log(`password`, password)

const ref = 'c7c2de28d81d3da4a386fc8444d574f2';
const config = axios.create({
    baseURL:'https://www.jsonbulut.com/json/',
    timeout: 15000,
    //headers: { 'jwt' : 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c' },
    //auth: {
      // username: '',
      // password: ''
    //}
})

// login
export const login = ( email:string, password: string ) => {

    const url = 'userLogin.php'
    const params = {
        ref: ref,
        userEmail: email,
        userPass: password,
        face: "no"
    }
    return config.get(url, { params })

}

export const product = async ( start:number, count:number  ) => {

    const url = 'product.php'
    const params = {
        ref: ref,
        start: start,
        count: count
    }
    return await config.get(url, { params })

}
