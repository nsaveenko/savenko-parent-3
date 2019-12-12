import {User} from "./User";

export class Like {
  id: number;
  idUser: number;
  idPost: number;
  userByIdUser: User;
}
