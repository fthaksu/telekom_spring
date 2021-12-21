import React from 'react';
import ReactDOM from 'react-dom';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

// components
import NavMenu from './components/NavMenu';

// import pages
import App from './App';
import Product from './Product';
import Detail from './Detail';


const objRoute = 
<Router>
  <NavMenu />
  <Routes>
    <Route path="/" element={<App/>} ></Route>
    <Route path="/product" element={<Product/>}></Route>
    <Route path="/detail/:id" element={<Detail/>}></Route>
  </Routes>
</Router>


ReactDOM.render( objRoute, document.getElementById('root'));
reportWebVitals();
