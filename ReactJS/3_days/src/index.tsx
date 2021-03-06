import React from 'react';
import ReactDOM from 'react-dom';
import reportWebVitals from './reportWebVitals';
import 'semantic-ui-css/semantic.min.css';
import 'react-toastify/dist/ReactToastify.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import { PrivateRoute } from './PrivateRoute'; 

// redux
import { Provider } from 'react-redux';
import { store } from './ReduxConfig';

// import Pages
import Login from './Login';
import Error from './Error';
import Dashboard from './Dashboard';
import Profile from './Profile';



const router = 
<Provider store={store}>
<Router>
  <Routes>
    <Route path="/" element={<Login />} ></Route>
    <Route path='/dashboard' element={ <PrivateRoute element={<Dashboard />} /> } />
    <Route path='/profile' element={ <PrivateRoute element={<Profile />} /> } />
    <Route path="*" element={<Error />} ></Route>
  </Routes>
</Router>
</Provider>


ReactDOM.render( router, document.getElementById('root') );
reportWebVitals();
