const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.auth.token,
  avatar: state => state.auth.avatar,
  name: state => state.auth.name,
  userList: state => state.user.userList,

  visitedViews: state => state.tagsView.visitedViews,
  cachedViews: state => state.tagsView.cachedViews,   
  permission_routes: state => state.permission.routes

}
export default getters
