import React from 'react';
import ReactDOM from 'react-dom';
import reportWebVitals from './reportWebVitals';
import 'semantic-ui-css/semantic.min.css';
import 'react-toastify/dist/ReactToastify.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'

// import Pages
import Login from './Login';
import Error from './Error';
import Dashboard from './Dashboard';

const router = 
<Router>
  <Routes>
    <Route path="/" element={<Login />} ></Route>
    <Route path="/dashboard" element={< Dashboard />}></Route>
    <Route path="*" element={<Error />} ></Route>
  </Routes>
</Router>


ReactDOM.render( router, document.getElementById('root') );
reportWebVitals();
