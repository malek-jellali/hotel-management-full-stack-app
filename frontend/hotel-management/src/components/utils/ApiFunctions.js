import axios from "axios"
//import { c } from "vite/dist/node/types.d-aGj9QkWt"


//axios enables to get the rest callq to the backend 
export const api= axios.create ({
    baseURL : "http://localhost:9129"
})

//this function adds a new room to the database 
export async function addRoom(photo , roomType , roomPrice){
    const formData = new FormData()
    formData.append("photo", photo)
    formData.append("roomType", roomType)
    formData.append("roomPrice", roomPrice)


    const response = await api.post("/rooms/add/new-room" , formData)
    if(response.status === 201){
        return true 
    }else{
        return false

    } 

}

//this function gets all room types from the database 

export async function getRoomTypes(){
    try {
        const response = await api.get("/rooms/roomTypes")
        return response.data

    } catch(error){
        throw new Error("Error fetching room types")

    }
}



    

