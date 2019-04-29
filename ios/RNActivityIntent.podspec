
Pod::Spec.new do |s|
  s.name         = "RNActivityIntent"
  s.version      = "1.0.0"
  s.summary      = "RNActivityIntent"
  s.description  = <<-DESC
                  RNActivityIntent
                   DESC
  s.homepage     = ""
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "author" => "author@domain.cn" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/author/RNActivityIntent.git", :tag => "master" }
  s.source_files  = "RNActivityIntent/**/*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  #s.dependency "others"

end

  