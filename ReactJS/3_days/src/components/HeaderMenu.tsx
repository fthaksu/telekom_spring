import React, { useEffect, useState } from 'react'
import { Navigate, NavLink, useLocation } from 'react-router-dom'
import { Event } from 'react-toastify/dist/core'
import { Button, Input, Menu, Modal } from 'semantic-ui-react'
import { Bilgiler } from '../models/ILogin'
import { isLogin } from '../Util'

function HeaderMenu() {

    // path ?
    const loc = useLocation()

    const [isLogOut, setIsLogOut] = useState(false)
    const [alertStatus, setAlertStatus] = useState(false)
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

    const fncLogOut = () => {
        // session clear
        sessionStorage.removeItem("user")
        // storge clear
        localStorage.removeItem("user")
        setAlertStatus(false)
        setIsLogOut(true)
    }

    // logout modal
    const fncModal = () => {
        return <Modal
        size={'mini'}
        open={alertStatus}
        onClose={() => setAlertStatus(false) }
      >
        <Modal.Header>Logout</Modal.Header>
        <Modal.Content>
          <p>Are you sure?</p>
        </Modal.Content>
        <Modal.Actions>
          <Button negative onClick={() =>  setAlertStatus(false)  }>
            No
          </Button>
          <Button positive onClick={() => fncLogOut() }>
            Yes
          </Button>
        </Modal.Actions>
      </Modal>
    }


    return (
        <Menu secondary>
            { fncModal() }
            { isLogOut && <Navigate to='/' /> }
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
                onClick={ (e, { name }) => setAlertStatus(true) }
            />
            </Menu.Menu>
        </Menu>
    )
}

export default HeaderMenu
