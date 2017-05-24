package controllers

import play.api.data.Form
import play.api.mvc.{Action, Controller}
import play.api.data.Forms._
import readme.{TableOfContentHelper, ReadmeForm}

class TableOfContentController  extends Controller{

  val userForm = Form(
    mapping(
      "description" -> nonEmptyText
    )(ReadmeForm.apply)(ReadmeForm.unapply)
  )

  val startContent: String =
    """Example :
# Title 1
## Title 2
### Title 3"""

  def readme = Action {
    Ok(views.html.readme(startContent))
  }

  def redirectContentTable = Action { implicit request =>
    val form: ReadmeForm = userForm.bindFromRequest.get
    Ok(views.html.readme(form.description, TableOfContentHelper.convert(form.description)))
  }

}