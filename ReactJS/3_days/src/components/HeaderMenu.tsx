import React, { useEffect, useState } from 'react'
import { NavLink, useLocation } from 'react-router-dom'
import { Event } from 'react-toastify/dist/core'
import { Input, Menu } from 'semantic-ui-react'
import { Bilgiler } from '../models/ILogin'
import { isLogin } from '../Util'

function HeaderMenu() {

    // path ?
    const loc = useLocation()

    const [activeItem, setActiveItem] = useState('dashboard')
    const bil:Bilgiler = { }
    const [user, setUser] = useState<Bilgiler>(bil)
    useEffect(() => {
        const bilgi = isLogin()
        if ( bilgi !== null ) {
            setUser(bilgi)
        }
    }, [])

    const gotoPage = ( path:string ) => {
        setActiveItem(path)
    }

    return (
        <Menu secondary>
            <Menu.Item active={ loc.pathname === '/dashboard' ? true : false }>
                <NavLink to="/dashboard" >Dashboard</NavLink>
            </Menu.Item>
            <Menu.Item active={ loc.pathname === '/profile' ? true : false }>
                <NavLink to="/profile" >Profile</NavLink>
            </Menu.Item>
            <Menu.Item
            name='friends'
            active={activeItem === 'friends'}
            onClick={ (e, { name }) => gotoPage(name!) }
            />
            <Menu.Item> Sn. { user!.userName } { user!.userSurname }</Menu.Item>
            <Menu.Menu position='right'>
            <Menu.Item>
                <Input icon='search' placeholder='Search...' />
            </Menu.Item>
            <Menu.Item
                name='logout'
                active={activeItem === 'logout'}
                onClick={ (e, { name }) => gotoPage(name!) }
            />
            </Menu.Menu>
        </Menu>
    )
}

export default HeaderMenu
