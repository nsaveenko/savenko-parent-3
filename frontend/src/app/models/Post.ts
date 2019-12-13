import {User} from "./User";

export class Post {
  id: number;
  idUser: number;
  filePath: string;
  datePost: number;
  text: string;
  file: FormData;
  userByIdUser: User;
  isLiked: boolean;
  likesCount: number;
  postsCount: number;
}
