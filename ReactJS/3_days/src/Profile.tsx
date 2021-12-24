import React, { useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { Button, Form, FormField, Table } from 'semantic-ui-react'
import { INote } from './models/INote'
import { IAction } from './reducers/NoteReducer'
import { NoteType } from './types/NoteType'
import {Helmet} from "react-helmet";

function Profile() {

    const state = useSelector( (state:{"noteReducer":[INote]}) => state)
    const dispatch = useDispatch()
    
    const [title, setTitle] = useState("")
    const [detail, setDetail] = useState("")

    const send = () => {

        const note: INote = {
            id: Math.random() ,
            title: title,
            detail: detail
        }

        const action: IAction = {
            type: NoteType.NOTE_ADD,
            payload: note
        }

        dispatch(action)
    }


    const deleteItem = (item: INote) => {
        const action: IAction = {
            type: NoteType.NOTE_DELETE,
            payload: item
        }
        dispatch(action)
    }


    return (
        <div>
            <h1>Welcome Profile</h1>
            <Helmet>
                <title>Profile</title>
                <meta name="description" content="Profile description"></meta>
            </Helmet>
            <Form>
            <FormField>
                <label>Title</label>
                <input onChange={(evt) => setTitle(evt.target.value)} placeholder='Title' />
            </FormField>
            <FormField>
                <label>Detail</label>
                <input onChange={(evt) => setDetail(evt.target.value)} placeholder='Title' />
            </FormField>
            <Button onClick={() => send() } >Send</Button>
            </Form>

            <Table singleLine>
                <Table.Header>
                <Table.Row>
                    <Table.HeaderCell>ID</Table.HeaderCell>
                    <Table.HeaderCell>Title</Table.HeaderCell>
                    <Table.HeaderCell>Detail</Table.HeaderCell>
                    <Table.HeaderCell>Delete</Table.HeaderCell>
                </Table.Row>
                </Table.Header>

                <Table.Body>
                { state.noteReducer.map( (item, index) => {
                    return (
                    <Table.Row key={index}>
                        <Table.Cell>{item.id}</Table.Cell>
                        <Table.Cell>{item.title}</Table.Cell>
                        <Table.Cell>{item.detail}</Table.Cell>
                        <Table.Cell> <Button onClick={(evt) => deleteItem(item) } color='red' >Delete</Button>  </Table.Cell>
                    </Table.Row>
                    )
                } ) }
                

                
                </Table.Body>
            </Table>


        </div>
    )
}

export default Profile
