import {User} from "./User";

export class Subscriber {
  id: number;
  userByIdFollowers: User ;
  userByIdFollowing: User;
  isSubscribed: boolean;
}
