import { createContext } from "react"

interface IContext {
    id:string
}

export const data:IContext = { id: "100" }

export const userContext = createContext<IContext>(data);