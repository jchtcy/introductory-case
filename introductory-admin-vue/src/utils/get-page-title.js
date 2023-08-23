import defaultSettings from '@/settings'

const title = defaultSettings.title || '前后端分离入门项目'

export default function getPageTitle(pageTitle) {
  if (pageTitle) {
    return `${pageTitle} - ${title}`
  }
  return `${title}`
}
