import React, { useContext, useEffect, useState } from 'react'
import { Helmet } from 'react-helmet'
import { Dimmer, Image, Loader, Menu, MenuItem, Table, TableBody, TableCell, TableFooter, TableHeader, TableHeaderCell, TableRow } from 'semantic-ui-react'
import { userContext } from './Context'
import { Bilgiler, IProdcut } from './models/IProdcut'
import { product } from './Services'

function Dashboard() {

    // use context
    const context = useContext(userContext)

    const [arr, setArr] = useState<Bilgiler[]>([])
    const [status, setStatus] = useState(false)

    // page
    const [count, setCount] = useState(3)
    const [start, setStart] = useState(0)
    const [total, setTotal] = useState(0)
    const [itemCount, setItemCount] = useState<number[]>([])


    useEffect(() => {
        productResult(start)
    }, [])


    const productResult = (start:number) => {
        setStatus(true)
        product(start, count).then( res => {
            const obj:IProdcut = res.data
            setArr( obj.Products![0].bilgiler! )
            const cTotal = Number(obj.Products![0].total)
            setTotal( cTotal )

            // pageCount
            let c = Math.ceil( cTotal / count )
            if ( c === 0 ) {
                c = 1;
            }
            const arr:number[] = [];
            for (let index = 0; index < c; index++) {
                arr.push(index)
            }
            setItemCount(arr)

            setStatus(false)
        })
    }

    return (
        <div>
            <Helmet>
                <title>Dashboard</title>
                <meta name="description" content="Dashboard description"></meta>
            </Helmet>
            <h1>Welcome DahsBoard - { context.id }</h1>
            
            <Loader active={status} inline >Loading</Loader>
            
            <div>Total: {count} / { total } - Page: { itemCount.length }</div>
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

                <TableFooter>
                    <TableRow>
                        <TableHeaderCell colSpan={4}>
                            <Menu floated='right' pagination>
                                { itemCount.map( (item, index) => {
                                    return (
                                        <MenuItem active={ index * count === 3 ? true : false } onClick={() => productResult( index * count ) } as='a' key={index}> { index + 1 } </MenuItem>
                                    )
                                } ) }
                            </Menu>
                        </TableHeaderCell>
                    </TableRow>
                </TableFooter>

            </Table>
        </div>
    )
}

export default Dashboard
