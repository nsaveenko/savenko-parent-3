import {Post} from "./Post";

export class Complaint {
  id: number;
  idUser: number;
  dateComplaint: number;
  complaint: string;
  postId: number;
  idStatusComplaint: number;
  isReviewed: boolean;
  message: string;
  username: string;
  post: Post;
}
