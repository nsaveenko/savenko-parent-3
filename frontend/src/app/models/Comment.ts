import {User} from "./User";

export class Comment {
  id: number;
  idUser: number;
  tex: string;
  dataPost: number;
  idPost: number;
  userByIdUser: User;
}
