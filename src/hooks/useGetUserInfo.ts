import { useGetUserInfo as useGetUserInfoAPI } from "@/api/users/queries";
import { useAuth } from "@/app/context";

export const useGetUserInfo = () => {
  const { userId } = useAuth();
  const { data, refetch} = useGetUserInfoAPI({
    variables: Number(userId),
    enabled: !!userId,
  });

  return {
    data,
    refetch
  };
};
