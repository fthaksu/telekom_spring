import React from 'react'
import { Navigate, Route, Routes } from 'react-router-dom'
import HeaderMenu from './components/HeaderMenu'
import { data, userContext } from './Context'
import { isLogin } from './Util'

interface IProps {
    element: JSX.Element
}


export const PrivateRoute = (  props: IProps  ) => {
    
    const rObj = isLogin() !== null ? <> <userContext.Provider value={ { id: isLogin()!.userId! } }> <HeaderMenu/>{props.element}  </userContext.Provider> </> : <Navigate to="/" />
    return rObj;
}

