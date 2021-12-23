import React from 'react'
import { Navigate, Route, Routes } from 'react-router-dom'
import HeaderMenu from './components/HeaderMenu'
import { isLogin } from './Util'

interface IProps {
    element: JSX.Element
}


export const PrivateRoute = (  props: IProps  ) => {
    
    const rObj = isLogin() !== null ? <> <HeaderMenu/>{props.element}</> : <Navigate to="/" />
    return rObj;
}

