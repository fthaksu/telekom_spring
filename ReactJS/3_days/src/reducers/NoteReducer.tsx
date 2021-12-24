import { INote } from "../models/INote"
import { NoteType } from "../types/NoteType"

export interface IAction {
    type: NoteType,
    payload: INote
}

const data:INote[] = []

export const noteReducer = ( state=data, action: IAction ) => {

    switch (action.type) {

    case NoteType.NOTE_ADD:
    return [...state, action.payload]

    case NoteType.NOTE_LIST:
    return [...state]

    case NoteType.NOTE_DELETE:
        const index = state.findIndex( item => item === action.payload )
        state.splice( index, 1)
        return [...state]
    
    default:
        return [...state]
    }

} 