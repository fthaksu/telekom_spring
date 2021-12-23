import {Buffer} from 'buffer'
import { Bilgiler } from './models/ILogin';

// is login control
export const isLogin = () => {

   // Remember Me Control
   const stRemember = localStorage.getItem("user")
   if ( stRemember !== null ) {
      sessionStorage.setItem("user", stRemember)
   }

   const stUser = sessionStorage.getItem("user");
   if ( stUser !== null ) {
      try {
         const decString = Buffer.from(stUser, 'base64').toString('ascii');
         const jsonObj:Bilgiler = JSON.parse(decString);
         if ( jsonObj ) {
            // service user control
            return jsonObj;
         }else {
            return null;
         }
      } catch (error) {
         return null;
      }
   }else {
      return null;
   }

}




export const emailValid = ( data:string ) => {
   
   const pattern = /[a-zA-Z0-9]+[\.]?([a-zA-Z0-9]+)?[\@][a-z]{3,9}[\.][a-z]{2,5}/g;
   const result = pattern.test(data);
   return result;

}