import React, { useEffect, useState } from 'react'
import { Dimmer, Image, Loader, Table, TableBody, TableCell, TableHeader, TableHeaderCell, TableRow } from 'semantic-ui-react'
import { Bilgiler, IProdcut } from './models/IProdcut'
import { product } from './Services'

function Dashboard() {

    const [arr, setArr] = useState<Bilgiler[]>([])
    const [status, setStatus] = useState(false)

    useEffect(() => {
        setStatus(true)
        product().then( res => {
            const obj:IProdcut = res.data
            setArr( obj.Products![0].bilgiler! )
            setStatus(false)
        })
    }, [])

    return (
        <div>
            <h1>Welcome DahsBoard</h1>
            
            <Loader active={status} inline >Loading</Loader>
            
            <Table singleLine>
                <TableHeader>
                    <TableRow>
                        <TableHeaderCell>ID</TableHeaderCell>
                        <TableHeaderCell>Name</TableHeaderCell>
                        <TableHeaderCell>Price</TableHeaderCell>
                        <TableHeaderCell>Image</TableHeaderCell>
                    </TableRow>
                </TableHeader>
                <TableBody>

                    { arr.map( (item, index) => {
                        return <TableRow key={index}>
                            <TableCell>{ item.productId }</TableCell>
                            <TableCell>{ item.productName }</TableCell>
                            <TableCell>{ item.price }</TableCell>
                            <TableCell><Image src={ item.images![0].normal } size={'tiny'} /></TableCell>
                         </TableRow>
                     } ) }


                </TableBody>
            </Table>
        </div>
    )
}

export default Dashboard
