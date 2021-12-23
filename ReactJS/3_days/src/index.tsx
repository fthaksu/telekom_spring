import React from 'react';
import ReactDOM from 'react-dom';
import reportWebVitals from './reportWebVitals';
import 'semantic-ui-css/semantic.min.css';
import 'react-toastify/dist/ReactToastify.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import { PrivateRoute } from './PrivateRoute'; 

// import Pages
import Login from './Login';
import Error from './Error';
import Dashboard from './Dashboard';
import Profile from './Profile';


const router = 
<Router>
  <Routes>
    <Route path="/" element={<Login />} ></Route>
    <Route path='/dashboard' element={ <PrivateRoute element={<Dashboard />} /> } />
    <Route path='/profile' element={ <PrivateRoute element={<Profile />} /> } />
    <Route path="*" element={<Error />} ></Route>
  </Routes>
</Router>


ReactDOM.render( router, document.getElementById('root') );
reportWebVitals();
