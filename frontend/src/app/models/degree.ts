export class Degree{
    
    id: Number;
    degree_id: String;
    module: String;
    day: String;
    time: String;
    venue: String;

    constructor(id,degree_id,module,day,time,venue){
        this.id = id
        this.degree_id = degree_id
        this.module = module
        this.day = day
        this.time = time
        this.venue = venue;
    }

   
   
    /*
    id: number;
    name: string;
    description: string;
    price: number;
    imageUrl: string;
  
    constructor(id, name, description = '', price = 0, imageUrl = 'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcR608TWmLRWFNYPlY5xgKkgZPYe7mwv0GDMDtAS9nRdlVo4aytG') {
      this.id = id
      this.name = name
      this.description = description
      this.price = price
      this.imageUrl = imageUrl
    }
    */
}