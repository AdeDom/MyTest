package com.adedom.mytest

data class MainModel(
    val id: Int? = null,
    val image: String? = null,
    val title: String? = null,
    val subTitle: String? = null,
)

interface MainRepository {

    fun fetchMainList(): List<MainModel>
}

class MainRepositoryImpl(
    private val mainLocalDataSource: MainLocalDataSource,
    private val mainRemoteDataSource: MainRemoteDataSource,
) : MainRepository {

    override fun fetchMainList(): List<MainModel> {
        try {
            val list = mapperMainList(mainRemoteDataSource.fetchMainList())
            mainLocalDataSource.insertMainData(list)
            return list
        } catch (e: Throwable) {
            e.printStackTrace()
            return emptyList()
        }
    }

    private fun mapperMainList(list: List<MainModel>): List<MainModel> {
        return list
            .filter { data ->
                data.image != null
            }
            .distinctBy { data ->
                data.id
            }
            .map { data ->
                MainModel(
                    id = data.id,
                    image = data.image,
                    title = data.title ?: "-",
                    subTitle = data.subTitle ?: "-",
                )
            }
    }
}

interface MainLocalDataSource {

    fun insertMainData(list: List<MainModel>)

    fun getMainData(): List<MainModel>
}

class MainLocalDataSourceImpl : MainLocalDataSource {

    private var list = listOf<MainModel>()

    override fun insertMainData(list: List<MainModel>) {
        this.list = list
    }

    override fun getMainData(): List<MainModel> {
        return this.list
    }
}

interface MainRemoteDataSource {

    fun fetchMainList(): List<MainModel>
}

class MainRemoteDataSourceImpl : MainRemoteDataSource {

    override fun fetchMainList(): List<MainModel> {
        return listOf(
            MainModel(
                id = 1,
                image = "https://www.adslthailand.com/uploads/moxie/2019/android-q.png",
                title = "What is Lorem Ipsum?",
                subTitle = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
            ),
            MainModel(
                id = 2,
                image = "https://br.atsit.in/th/wp-content/uploads/2021/06/e0b884e0b8b8e0b893e0b8a5e0b8b1e0b881e0b8a9e0b893e0b8b0-ios-15-e0b897e0b8b5e0b988e0b984e0b894e0b989e0b8a3e0b8b1e0b89ae0b881e0b8b2e0b8a3.png",
                title = "Why do we use it?",
                subTitle = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
            ),
            MainModel(
                id = 3,
                image = "https://images.prismic.io/impactio-blog/2575689d-8dfe-4d7c-b6a7-f33b170231b8_What+Does+a+Dart+and+Flutter+Developer+Do.png?auto=compress,format",
                title = "Where does it come from?",
                subTitle = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.",
            ),
            MainModel(
                id = 3,
                image = null,
                title = null,
                subTitle = null,
            ),
            MainModel(
                id = 4,
                image = "https://cdn.techinasia.com/wp-content/uploads/2013/09/silicon_valley.jpg",
                title = "Where can I get some?",
                subTitle = "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.",
            ),
            MainModel(
                id = 5,
                image = "https://img.blognone.com/jobs/prod/524x274/cover/choco-card-enterprise-co-ltd.jpg",
                title = "Mad",
                subTitle = null,
            ),
            MainModel(
                id = 5,
                image = "https://www.blognone.com/sites/default/files/externals/03bdedf393307f71db391af65d74048a.png",
                title = null,
                subTitle = "Sili",
            ),
            MainModel(
                id = 5,
                image = null,
                title = "Mad",
                subTitle = "Sili",
            ),
        )
    }
}