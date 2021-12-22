import React, {  useRef, useState } from 'react'
import { Button, Checkbox, Form, Grid, GridColumn, GridRow, Icon } from 'semantic-ui-react'
import { ToastContainer, toast } from 'react-toastify';
import { emailValid } from './Util';
import { login } from './Services';
import { ILogin } from './models/ILogin';
import { Navigate } from 'react-router-dom';
import {Buffer} from 'buffer'


function Login() {

    // useRef
    const refEmail = useRef<HTMLInputElement>(null)
    const refPassword = useRef<HTMLInputElement>(null)

    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const [remember, setRemember] = useState(false)
    const [loginStatus, setLoginStatus] = useState(false)

    const formLogin = (evt:React.FormEvent ) => {
        evt.preventDefault()
        
        if ( !emailValid(email) ) {
            toast.dismiss()
            toast("Email Format Fail")
            
            refEmail.current?.focus()
        }else if ( password === '' || password.length < 3 ) {
            toast.dismiss()
            toast("Password not empity or min char 3 length")
            refPassword.current?.focus()
        }else {
            // axios send form
            login(email, password).then( res => {
                const obj:ILogin = res.data
                if ( obj.user ) {
                    const user =  obj.user![0]
                    if ( user.durum ) {
                        toast.success(user.mesaj!)
                        const bilgiler = user.bilgiler!
                        const stBilgiler = JSON.stringify(bilgiler)
                        sessionStorage.setItem("user", Buffer.from(stBilgiler).toString('base64') );
                        
                        setTimeout(() => {
                            setLoginStatus(user.durum!)
                        }, 2000);
                    }else {
                        toast.error(user.mesaj!)
                    }
                }
                
            })
        }

    }


    return (
        <Grid columns={3} stackable >
            <ToastContainer limit={1} />

            { loginStatus && <Navigate to="/dashboard" replace /> }
            
            <GridRow>
                <GridColumn></GridColumn>
                <GridColumn>
                    <h1>User Login</h1>
                    <Form onSubmit={(evt) => formLogin(evt) } >
                        <Form.Field>
                            <label>E-Mail</label>
                            <input ref={refEmail} onChange={(evt) => setEmail(evt.target.value)} placeholder='E-Mail' />
                        </Form.Field>
                        <Form.Field>
                            <label>Password</label>
                            <input ref={refPassword} onChange={(evt) => setPassword(evt.target.value)} type='password' placeholder='Password' />
                        </Form.Field>
                        <Form.Field>
                            <Checkbox onChange={(evt) => setRemember( !remember )} label='Remember' />
                        </Form.Field>
                        <Button animated type='submit' color='orange' >
                            <Button.Content visible>Login</Button.Content>
                            <Button.Content hidden>
                                <Icon name='arrow right' />
                            </Button.Content>
                        </Button>
                    </Form>
                </GridColumn>
                <GridColumn></GridColumn>
            </GridRow>
        </Grid>
    )
}

export default Login
