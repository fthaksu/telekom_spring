import { combineReducers, createStore } from "redux";
import { noteReducer } from "./reducers/NoteReducer";

const combine = combineReducers({
    noteReducer,
})

export const store = createStore(combine)